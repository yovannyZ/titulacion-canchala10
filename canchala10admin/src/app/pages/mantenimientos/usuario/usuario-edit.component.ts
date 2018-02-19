import { NotificationsComponent } from './../../components/notifications/notifications.component';
import { Usuario } from './../../../@core/model/usuario';
import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute,Params} from '@angular/router';
import { UsuarioService } from '../../../@core/data/usuario.service';
import { TipoUsuarioService } from '../../../@core/data/tipo-usuario.service';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';

@Component({
  selector: 'ngx-usuario-edit',
  templateUrl: './usuario-edit.component.html'
})

export class UsuarioEditComponent implements OnInit {

  public usuario:Usuario;
  public tipoUsuarios;
  config: ToasterConfig;
  position = 'toast-top-center';
  animationType = 'flyleft';
  timeout = 4000;
  toastsLimit = 5;
  isNewestOnTop = true;
  isHideOnClick = true;
  isDuplicatesPrevented = false;
  isCloseButton = true;


  constructor(
    private _route:ActivatedRoute,
    private _usuarioService:UsuarioService,
    private _router:Router,
    private _tipoUsuarioServices: TipoUsuarioService,
    private toasterService: ToasterService) {

    this.usuario = new Usuario('','','',0);
   }

  ngOnInit() {
    this.listarTipoUsuario();
    this.getUsuario();
  }

  getUsuario(){
   this._route.params.forEach((params:Params)=>{
     let id = params['id'];
     this._usuarioService.getUsuario(id).subscribe(
      res => {
          if(res.status){
            this.usuario = res.response;
          }
      },
      err => {
        var error = <any>err;
        console.log(error);
      }
     );
   })
  }

  listarTipoUsuario(){
    this._tipoUsuarioServices.getTipoUsuarios().subscribe(
      result => {
        if(result.status){
          this.tipoUsuarios = result.response;
        }
        else{
          console.log( result.response);
        }
      },
      error => {
        var err = <any>error;
        console.log(err);
      }
    );
  }

  onSubmit(){
    let ususarioEdit = new Usuario(this.usuario.correo, this.usuario.clave, this.usuario.nombres, this.usuario.id_tipo_usuario);
  
    this._usuarioService.editUsuario(ususarioEdit)
    .subscribe(
      res => {
        if(res.status){
          this.showToast("info", "Éxito", "Usuario actualizado correctamente ");
          this.usuario = new Usuario("","","",0);
        }
      },
      err => {
        console.log(<any>err);
      }
    );
  }

  private showToast(type: string, title: string, body: string) {
    this.config = new ToasterConfig({
      positionClass: this.position,
      timeout: this.timeout,
      newestOnTop: this.isNewestOnTop,
      tapToDismiss: this.isHideOnClick,
      preventDuplicates: this.isDuplicatesPrevented,
      animation: this.animationType,
      limit: this.toastsLimit,
    });
    const toast: Toast = {
      type: type,
      title: title,
      body: body,
      timeout: this.timeout,
      showCloseButton: this.isCloseButton,
      bodyOutputType: BodyOutputType.TrustedHtml,
    };
    this.toasterService.popAsync(toast);
  }

}
