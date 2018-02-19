import { CampoAddComponent } from './campo/campo-add.component';
import { CampoComponent } from './campo/campo.component';
import { SedeComponent } from './sede/sede.component';
import { UsuarioEditComponent } from './usuario/usuario-edit.component';
import { UsuarioAddComponent } from './usuario/usuario-add.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MantenimientosComponent } from './mantenimientos.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { SedeAddComponent } from './sede/sede-add.component';
import { SedeEditComponent } from './sede/sede-edit.component';
import { CampoEditComponent } from './campo/campo-edit.component';


const routes: Routes = [{
  path: '',
  component: MantenimientosComponent,
  children: [{
    path: 'usuario',
    component: UsuarioComponent,
  },
  {
    path: 'usuario-add',
    component: UsuarioAddComponent,
  },
  {
    path: 'usuario-edit/:id',
    component: UsuarioEditComponent,
  },
  {
    path: 'sede',
    component: SedeComponent,
  },
  {
    path: 'sede-add',
    component: SedeAddComponent,
  },
  {
    path: 'sede-edit/:id',
    component: SedeEditComponent,
  },
  {
    path: 'campo',
    component: CampoComponent,
  },
  {
    path: 'campo-add',
    component: CampoAddComponent,
  },
  {
    path: 'campo-edit/:id',
    component: CampoEditComponent,
  }],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MantenimientosRoutingModule { }

export const routedComponents = [
    MantenimientosComponent,
    UsuarioComponent,
    UsuarioAddComponent,
    UsuarioEditComponent,
    SedeAddComponent,
    SedeComponent,
    SedeEditComponent,
    CampoComponent,
    CampoAddComponent,
    CampoEditComponent
];
