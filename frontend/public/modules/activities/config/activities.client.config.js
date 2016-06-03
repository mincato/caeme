'use strict';

// Configuring the Categories module
angular.module('activities').run(['Menus',
	function(Menus) {
		// Set top bar menu items
		Menus.addMenuItem('topbar', 'Actividades', 'activities', 'dropdown', 'actividades');
		Menus.addSubMenuItem('topbar', 'activities', 'Reporte de Actividades', 'actividades/reportes');
	}
]);