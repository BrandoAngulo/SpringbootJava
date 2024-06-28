import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { LogsService } from './logs.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css'],
})
export class LogsComponent implements OnInit {
  private logsService = inject(LogsService);
  private formBuilder = inject(FormBuilder);

  //Formulario
  public formularioLogs: FormGroup;

  public clientes = ['420', '50'];
  public listaLogs: String[];

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  /// Tabla
  public tableData: Array<String> = new Array<String>();
  public dataSource: MatTableDataSource<String>;
  public displayedColumns: string[] = ['nombreArchivo', 'acciones'];
  public estado: boolean = true;
  public edit: boolean = true;
  public eliminar: boolean = true;

  constructor() {
    this.buildForm();
  }

  ngOnInit(): void {
    this.obtenerLogs();
  }

  buildForm(): void {
    this.formularioLogs = this.formBuilder.group({
      cliente: ['', [Validators.required]],
    });
  }

  obtenerLogs(): void {
    this.logsService.obtenerLogs().subscribe({
      next: (respuesta: String[]) => {
        this.tableData = respuesta;
        this.llenarTabla();
      },
    });
  }

  descargarLog(nombreArchivo: string): void {
    this.logsService.descargarLog(nombreArchivo).subscribe({
      next: (blob: Blob) => {
        if (blob) {
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = nombreArchivo;
          document.body.appendChild(a);
          a.click();
          document.body.removeChild(a);
          window.URL.revokeObjectURL(url);
        }
      },
    });
  }

  llenarTabla(): void {
    this.dataSource = new MatTableDataSource<String>(this.tableData);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  filtrarBusqueda(value: string) {
    this.dataSource.filterPredicate = (data, filter) => {
      return data.toLowerCase().includes(filter);
    };
    this.dataSource.filter = value.trim().toLowerCase();
  }

  limpiarFormulario(): void {
    this.formularioLogs.reset();
  }
}
