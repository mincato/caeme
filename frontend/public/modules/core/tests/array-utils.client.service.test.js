'use strict';

describe('Test suite for ArrayUtils service', function() {

	beforeEach(module(ApplicationConfiguration.applicationModuleName));

	it('Se prueba que se pueda buscar la posición de un objeto en un array por _id', inject(function(ArrayUtils) {

		var array = [{_id : 25, nombre : 'hola'}, {_id : 34, nombre : 'chau'}, {_id : 12, nombre : 'bonchorno'}];

		var index = ArrayUtils.indexOfByProperty(array, '_id', 34);

		expect(index).toBe(1);
	}));

	it('Se prueba que al buscar un valor que no existe, indexOfByProperty devuelve -1', inject(function(ArrayUtils) {

		var array = [{_id : 25, nombre : 'hola'}, {_id : 34, nombre : 'chau'}, {_id : 12, nombre : 'bonchorno'}];

		var index = ArrayUtils.indexOfByProperty(array, '_id', 18);

		expect(index).toBe(-1);
	}));

	it('Se prueba que al buscar por un atributo que no existe, indexOfByProperty devuelve -1', inject(function(ArrayUtils) {

		var array = [{_id : 25, nombre : 'hola'}, {_id : 34, nombre : 'chau'}, {_id : 12, nombre : 'bonchorno'}];

		var index = ArrayUtils.indexOfByProperty(array, 'noExiste', 25);

		expect(index).toBe(-1);
	}));
});
