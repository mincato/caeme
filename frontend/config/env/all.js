'use strict';

module.exports = {
	app: {
		title: 'activity-report-frontend',
		description: 'Usaremos la parte de AngularJS para apuntar a un servidor externo. Para este proyecto no se utilizará la parte de Node.js.',
		keywords: 'AngularJS'
	},
	port: process.env.PORT || 3000,
	templateEngine: 'swig',
	sessionSecret: 'MEAN',
	sessionCollection: 'sessions',
	assets: {
		lib: {
			css: [
				'public/lib/bootstrap/dist/css/bootstrap.css',
				'public/lib/bootstrap/dist/css/bootstrap-theme.css',
                'public/lib/components-font-awesome/css/font-awesome.css',
                'public/lib/angular-chart.js/dist/angular-chart.css'
			],
			js: [
				'public/lib/angular/angular.js',
				'public/lib/angular-sanitize/angular-sanitize.js',
				'public/lib/angular-resource/angular-resource.js',
				'public/lib/angular-ui-router/release/angular-ui-router.js',
				'public/lib/angular-ui-utils/ui-utils.js',
				'public/lib/angular-bootstrap/ui-bootstrap-tpls.js',
				'public/lib/lodash/lodash.js',
				'public/lib/angular-simple-logger/dist/angular-simple-logger.js',
				'public/lib/angular-google-maps/dist/angular-google-maps.js',
				'public/lib/moment/moment.js',
				'public/lib/angular-moment/angular-moment.js',
				'public/lib/Chart.js/Chart.js',
				'public/lib/angular-chart.js/dist/angular-chart.js',
				'public/lib/chart.horizontalbar/Chart.HorizontalBar.js'
			]
		},
		css: [
			'public/modules/**/css/*.css'
		],
		js: [
			'public/config.js',
			'public/application.js',
			'public/modules/*/*.js',
			'public/modules/*/*[!tests]*/*.js'
		],
		tests: [
			'public/lib/angular-mocks/angular-mocks.js',
			'public/modules/*/tests/*.js'
		]
	}
};
