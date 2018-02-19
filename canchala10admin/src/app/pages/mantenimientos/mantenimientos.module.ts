import { CampoService } from './../../@core/data/campo.service';
import { TipoUsuarioService } from './../../@core/data/tipo-usuario.service';
import { NgModule } from '@angular/core';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ToasterModule } from 'angular2-toaster';
import { ThemeModule } from '../../@theme/theme.module';
import { MantenimientosRoutingModule , routedComponents } from './mantenimientos-routing.module';
import { UsuarioService } from '../../@core/data/usuario.service';
import { DataTableModule } from 'angular2-datatable/lib/DataTableModule';
import { ToasterService } from 'angular2-toaster';
import { ConfirmationPopoverModule } from 'angular-confirmation-popover';
import { SedeService } from '../../@core/data/sede.service';

@NgModule({
  imports: [
    ThemeModule,
    MantenimientosRoutingModule,
    Ng2SmartTableModule,
    DataTableModule,
    ToasterModule,
    ConfirmationPopoverModule.forRoot({
      confirmButtonType: 'danger' // set defaults here
    })
  ],
  declarations: [
    ...routedComponents,
  ],
  providers: [
    UsuarioService,
    TipoUsuarioService,
    ToasterService,
    SedeService,
    CampoService
  ]
})
export class MantenimientosModule { }