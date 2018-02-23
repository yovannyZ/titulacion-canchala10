<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/*
| -------------------------------------------------------------------------
| URI ROUTING
| -------------------------------------------------------------------------
| This file lets you re-map URI requests to specific controller functions.
|
| Typically there is a one-to-one relationship between a URL string
| and its corresponding controller class/method. The segments in a
| URL normally follow this pattern:
|
|	example.com/class/method/id/
|
| In some instances, however, you may want to remap this relationship
| so that a different class/function is called than the one
| corresponding to the URL.
|
| Please see the user guide for complete details:
|
|	https://codeigniter.com/user_guide/general/routing.html
|
| -------------------------------------------------------------------------
| RESERVED ROUTES
| -------------------------------------------------------------------------
|
| There are three reserved routes:
|
|	$route['default_controller'] = 'welcome';
|
| This route indicates which controller class should be loaded if the
| URI contains no data. In the above example, the "welcome" class
| would be loaded.
|
|	$route['404_override'] = 'errors/page_missing';
|
| This route will tell the Router which controller/method to use if those
| provided in the URL cannot be matched to a valid route.
|
|	$route['translate_uri_dashes'] = FALSE;
|
| This is not exactly a route, but allows you to automatically route
| controller and method names that contain dashes. '-' isn't a valid
| class or method name character, so it requires translation.
| When you set this option to TRUE, it will replace ALL dashes in the
| controller and method URI segments.
|
| Examples:	my-controller/index	-> my_controller/index
|		my-controller/my-method	-> my_controller/my_method
*/
$route['default_controller'] = 'rest_server';
$route['404_override'] = '';
$route['translate_uri_dashes'] = FALSE;

/*
| -------------------------------------------------------------------------
| Sample REST API Routes
| -------------------------------------------------------------------------
*/
$route['api/example/users/(:num)'] = 'api/example/users/id/$1'; // Example 4
$route['api/example/users/(:num)(\.)([a-zA-Z0-9_-]+)(.*)'] = 'api/example/users/id/$1/format/$3$4'; // Example 8

/*Route TIPO USUARIO*/
$route['api/tipousuario']['get'] = 'api/tipousuario/index';
$route['api/tipousuario/(:num)']['get'] = 'api/tipousuario/find/$1';
$route['api/tipousuario']['post'] = 'api/tipousuario/index';
$route['api/tipousuario']['put'] = 'api/tipousuario/index';
$route['api/tipousuario/(:num)']['delete'] = 'api/tipousuario/index/$1';

/*Route USUARIO*/
$route['api/usuario']['get'] = 'api/usuario/index';
$route['api/usuario/(:any)']['get'] = 'api/usuario/find/$1';
$route['api/usuario']['post'] = 'api/usuario/index';
$route['api/usuario']['put'] = 'api/usuario/index';
$route['api/usuario/(:any)']['delete'] = 'api/usuario/index/$1';
$route['api/usuario/(:any)/reservas']['get'] = 'api/usuario/getReservas/$1';


/*Route sede*/
$route['api/sede']['get'] = 'api/sede/index';
$route['api/sede/(:num)']['get'] = 'api/sede/find/$1';
$route['api/sede/(:num)/campos']['get'] = 'api/campo/getAllBySede/$1';
$route['api/sede']['post'] = 'api/sede/index';
$route['api/sede']['put'] = 'api/sede/index';
$route['api/sede/(:num)']['delete'] = 'api/sede/index/$1';

/*Route campo*/
$route['api/campo']['get'] = 'api/campo/index';
$route['api/campo/(:num)']['get'] = 'api/campo/find/$1';
$route['api/campo']['post'] = 'api/campo/index';
$route['api/campo']['put'] = 'api/campo/index';
$route['api/campo/(:num)']['delete'] = 'api/campo/index/$1';

/*Route horario*/
$route['api/horario']['get'] = 'api/horario/index';
$route['api/horario/(:num)']['get'] = 'api/horario/find/$1';
$route['api/horario']['post'] = 'api/horario/index';
$route['api/horario']['put'] = 'api/horario/index';
$route['api/horario/(:num)']['delete'] = 'api/horario/index/$1';

/*Route tarifa*/
$route['api/tarifa']['get'] = 'api/tarifa/index';
$route['api/tarifa/(:num)']['get'] = 'api/tarifa/find/$1';
$route['api/tarifa']['post'] = 'api/tarifa/index';
$route['api/tarifa']['put'] = 'api/tarifa/index';
$route['api/tarifa/(:num)']['delete'] = 'api/tarifa/index/$1';
$route['api/campo/(:num)/tarifas']['get'] = 'api/tarifa/getByCampo/$1';

/*Route reserva*/
$route['api/reserva']['get'] = 'api/reserva/index';
$route['api/reserva/(:num)']['get'] = 'api/reserva/find/$1';
$route['api/reserva']['post'] = 'api/reserva/index';
$route['api/reserva']['put'] = 'api/reserva/index';
$route['api/reserva/(:num)']['delete'] = 'api/reserva/index/$1';

/*Route reserva_detalle*/
$route['api/reserva_detalle']['get'] = 'api/ReservaDetalle/index';
$route['api/reserva_detalle/(:num)']['get'] = 'api/ReservaDetalle/find/$1';
$route['api/reserva_detalle']['post'] = 'api/ReservaDetalle/index';
$route['api/reserva_detalle']['put'] = 'api/ReservaDetalle/index';
$route['api/reserva_detalle/(:num)']['delete'] = 'api/ReservaDetalle/index/$1';
$route['api/campo/(:num)/(:any)/horarios']['get'] = 'api/ReservaDetalle/gethorasByFecha/$1/$2';