import {
  Component,
  OnInit,
  OnChanges,
  Input,
  Output,
  EventEmitter,
  ViewChild,
} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss'],
})
export class DataTableComponent implements OnInit, OnChanges {
  @Input() edit: boolean = false; //Editar un usuario por id
  @Input() estado: boolean = false; //Editar Estado de un usuaro
  @Input() editDto: boolean = false; //Editar un usuario por dto

  @Input() tableData: any; //Arreglo de los datos a mostrar
  @Input() columnHeader: any; //Nombres de las columnas de la tabla
  objectKeys = Object.keys;
  dataSource: any;

  @Output() id: EventEmitter<number>;
  @Output() item: EventEmitter<number>;
  @Output() dto: EventEmitter<number>;

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  constructor() {
    this.id = new EventEmitter();
    this.item = new EventEmitter();
    this.dto = new EventEmitter();
  }
  ngOnChanges(): void {
    this.dataSource = new MatTableDataSource(this.tableData);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit() {}

  filtrarBusqueda(value: string) {
    this.dataSource.filter = value.trim().toLowerCase();
  }

  editar(id: number) {
    this.item.emit(id);
  }

  editarDto(event: any) {
    this.dto.emit(event);
  }

  actualizarEstado(i: number) {
    this.id.emit(i);
  }
}
