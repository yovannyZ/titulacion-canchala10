import { UsuarioService } from './../../../@core/data/usuario.service';
import { Usuario } from './../../../@core/model/usuario';
import { Component, OnInit } from '@angular/core';
import { ToasterService, ToasterConfig, Toast, BodyOutputType } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';


@Component({
  selector: 'ngx-usuario',
  templateUrl: './usuario.component.html',
  styles: [`
    nb-card {
      transform: translate3d(0, 0, 0);
    }
  `],
})
export class UsuarioComponent implements OnInit {

  public Usuarios: Array<Usuario>;
  public filterQuery = "";
  public rowsOnPage = 5;
  public sortBy = "correo";
  public sortOrder = "asc";
  public popoverTitle: string = 'Eliminación';
  public popoverMessage: string = '¿Está seguro(a) de eliminar este registro?';
  public confirmClicked: boolean = false;
  public cancelClicked: boolean = false;
  config: ToasterConfig;
  position = 'toast-top-center';
  animationType = 'flyleft';
  timeout = 4000;
  toastsLimit = 5;
  isNewestOnTop = true;
  isHideOnClick = true;
  isDuplicatesPrevented = false;
  isCloseButton = true;

  constructor(private _usuarioService:UsuarioService, private toasterService: ToasterService) { }

  ngOnInit() {
    this.ListarUsuarios();
  }

  ListarUsuarios(){
    this._usuarioService.getAll().subscribe(
      result => {
        if(result.status){
          this.Usuarios = result.response;
        }
        else{
          console.log(result.response);
        }
      },
      error => {
        var err = <any>error;
        console.log(err);
      }
    );
  }

  deleteUsuario($event, correo:string){
    this._usuarioService.deleteUsuario(correo).subscribe(
      res=>{
        if(res.status){
          this.showToast("info", "Éxito", "Usuario eliminado correctamente ");
          this.ListarUsuarios();
        }
        else{
          console.log(res.response);
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