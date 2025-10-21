import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PoModule } from '@po-ui/ng-components';
import { PoTemplatesModule } from '@po-ui/ng-templates';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastroComentarioComponent } from './comentarios/cadastro-comentario/cadastro-comentario.component';
import { ComentariosComponent } from './comentarios/comentarios.component';
import { CadastroPaisesComponent } from './paises/cadastro-paises/cadastro-paises.component';
import { PaisesComponent } from './paises/paises.component';
import { CadastroPontosTuristicosComponent } from './pontos-turisticos/cadastro-pontos-turisticos/cadastro-pontos-turisticos.component';
import { PontosTuristicosComponent } from './pontos-turisticos/pontos-turisticos.component';
import { DetalhesPontoTuristicoComponent } from './pontos-turisticos/detalhes-ponto-turistico/detalhes-ponto-turistico.component';

@NgModule({
  declarations: [
    AppComponent,
    PaisesComponent,
    CadastroPaisesComponent,
    PontosTuristicosComponent,
    CadastroPontosTuristicosComponent,
    ComentariosComponent,
    CadastroComentarioComponent,
    DetalhesPontoTuristicoComponent
  ],
  imports: [
    BrowserModule,
    //para o angular se comunicar com o java
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    PoModule,
    RouterModule.forRoot([]),
    PoTemplatesModule,
  ],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
