'use strict';

module.exports = function(grunt) {
	// Unified Watch Object
	var watchFiles = {
		serverViews: ['app/views/**/*.*'],
		serverJS: ['gruntfile.js', 'server.js', 'config/**/*.js', 'app/**/*.js'],
		clientViews: ['public/modules/**/views/**/*.html'],
		clientJS: ['public/js/*.js', 'public/modules/**/*.js'],
		clientCSS: ['public/modules/**/*.css'],
		mochaTests: ['app/tests/**/*.js']
	};

	// Project Configuration
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		watch: {
			serverViews: {
				files: watchFiles.serverViews,
				options: {
					livereload: true
				}
			},
			serverJS: {
				files: watchFiles.serverJS,
				tasks: ['jshint'],
				options: {
					livereload: true
				}
			},
			clientViews: {
				files: watchFiles.clientViews,
				options: {
					livereload: true,
				}
			},
			clientJS: {
				files: watchFiles.clientJS,
				tasks: ['jshint', 'test'],
				options: {
					livereload: true
				}
			},
			clientCSS: {
				files: watchFiles.clientCSS,
				tasks: ['csslint'],
				options: {
					livereload: true
				}
			}
		},
		jshint: {
			all: {
				src: watchFiles.clientJS.concat(watchFiles.serverJS),
				options: {
					jshintrc: true
				}
			}
		},
		csslint: {
			options: {
				csslintrc: '.csslintrc',
			},
			all: {
				src: watchFiles.clientCSS
			}
		},
		uglify: {
			production: {
				options: {
					mangle: false
				},
				files: {
					'public/dist/application.min.js': 'public/dist/application.js'
				}
			}
		},
		cssmin: {
			combine: {
				files: {
					'public/dist/application.min.css': '<%= applicationCSSFiles %>'
				}
			}
		},
		nodemon: {
			dev: {
				script: 'server.js',
				options: {
					nodeArgs: ['--debug'],
					ext: 'js,html',
					watch: watchFiles.serverViews.concat(watchFiles.serverJS)
				}
			}
		},
		'node-inspector': {
			custom: {
				options: {
					'web-port': 1337,
					'web-host': 'localhost',
					'debug-port': 5858,
					'save-live-edit': true,
					'no-preload': true,
					'stack-trace-limit': 50,
					'hidden': []
				}
			}
		},
		ngAnnotate: {
			production: {
				files: {
					'public/dist/application.js': '<%= applicationJavaScriptFiles %>'
				}
			}
		},
		concurrent: {
			default: ['nodemon', 'watch'],
			debug: ['nodemon', 'watch', 'node-inspector'],
			options: {
				logConcurrentOutput: true,
				limit: 10
			}
		},
		env: {
			test: {
				NODE_ENV: 'test'
			},
			secure: {
				NODE_ENV: 'secure'
			},
			development: {
				NODE_ENV: 'development'
			}
		},
		mochaTest: {
			src: watchFiles.mochaTests,
			options: {
				reporter: 'spec',
				require: 'server.js'
			}
		},
		karma: {
			unit: {
				configFile: 'karma.conf.js'
			}
		},
		copy: {
			main: {
				expand: true,
				cwd: 'public/',
				src: ['modules/**/views/*.html', 'modules/**/img/**'],
				dest: 'public/dist'
			},
            fontsAwesome: {
                expand: true,
                cwd: 'public/lib/components-font-awesome/',
                src: ['fonts/**'],
                dest: 'public/dist'
            },
            fontsBootstrap: {
                expand: true,
                cwd: 'public/lib/bootstrap/',
                src: ['fonts/**'],
                dest: 'public/dist'
            }
		},
		clean: {
			main: ['public/dist'],
			app: ['public/dist/application.js']
		},
        less: {
            production: {
                options: {
                    compress: false,
                    yuicompress: false,
                    modifyVars: {
                        'fa-font-path': 'fonts',
                        'icon-font-path': '~"fonts/"'
                    }
                },
                files: {
                      // target.css file: source.less file
                      'public/lib/components-font-awesome/css/font-awesome.css': 'public/lib/components-font-awesome/less/font-awesome.less',
                      'public/lib/bootstrap/dist/css/bootstrap.css': 'public/lib/bootstrap/less/bootstrap.less'
                }
            },
            development: {
                options: {
                    compress: false,
                    yuicompress: false,
                    modifyVars: {
                        'fa-font-path': '~"../fonts"',
                        'icon-font-path': '~"../fonts/"'
                    }
                },
                files: {
                      // target.css file: source.less file
                      'public/lib/components-font-awesome/css/font-awesome.css': 'public/lib/components-font-awesome/less/font-awesome.less',
                      'public/lib/bootstrap/dist/css/bootstrap.css': 'public/lib/bootstrap/less/bootstrap.less'
                }
            }
        }        
	});

	// Load NPM tasks
	require('load-grunt-tasks')(grunt);
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-less');

	// Making grunt default to force in order not to break the project.
	grunt.option('force', true);

	// A Task for loading the configuration object
	grunt.task.registerTask('loadConfig', 'Task that loads the config into a grunt option.', function() {
		var init = require('./config/init')();
		var config = require('./config/config');

		grunt.config.set('applicationJavaScriptFiles', config.assets.lib.js.concat(config.assets.js));
		grunt.config.set('applicationCSSFiles', config.assets.lib.css.concat(config.assets.css));
	});

	// A Task to run swig to process the server.view.html templates
	grunt.registerTask('runSwig', 'Task that run swig for process the view templates.', function() {

		var swig  = require('swig');
		var init = require('./config/init')();
		var config = require('./config/config');

		var variables = {
			jsFiles: ['application.min.js'],
			cssFiles: ['application.min.css'],
			buildingDist: true
		};

		var indexProcessed = swig.renderFile(__dirname +'/app/views/index.server.view.html', variables);
		grunt.file.write(__dirname + '/public/dist/index.html', indexProcessed);

		var page404processed = swig.renderFile(__dirname +'/app/views/404.server.view.html', variables);
		grunt.file.write(__dirname + '/public/dist/404.html', page404processed);

		var page500processed = swig.renderFile(__dirname +'/app/views/500.server.view.html', variables);
		grunt.file.write(__dirname + '/public/dist/500.html', page500processed);

	});

	// Default task(s).
	grunt.registerTask('default', ['less:development', 'lint', 'test', 'concurrent:default']);

	// Debug task.
	grunt.registerTask('debug', ['lint', 'concurrent:debug']);

	// Secure task(s).
	grunt.registerTask('secure', ['env:secure', 'lint', 'concurrent:default']);

	// Lint task(s).
	grunt.registerTask('lint', ['jshint', 'csslint']);

	// Build task(s).
	grunt.registerTask('build', ['less:production', 'clean', 'lint', 'loadConfig', 'ngAnnotate', 'uglify', 'cssmin', 'runSwig', 'copy', 'clean:app']);
    
	// Test task.
	grunt.registerTask('test', ['env:development', 'karma:unit']);
};