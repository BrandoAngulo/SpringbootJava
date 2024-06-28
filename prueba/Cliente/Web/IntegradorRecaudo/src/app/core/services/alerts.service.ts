import { Injectable } from '@angular/core';
import Swal, { SweetAlertIcon } from 'sweetalert2';

// import Dtos
import { MessageExceptionDto } from 'src/app/data/schema/MessageExceptionDto';

@Injectable({
  providedIn: 'root',
})
export class AlertsService {
  constructor() {}

  toast(icon: SweetAlertIcon, title: string, timer: number = 3000) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: timer,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer);
        toast.addEventListener('mouseleave', Swal.resumeTimer);
      },
    });

    Toast.fire({
      icon: icon,
      title: title,
    });
  }

  fireError(errorDto: MessageExceptionDto) {
    Swal.fire({
      title: `Error [${errorDto.code}]`,
      html: `<b>Descripción:</b> ${errorDto.message}   </br>  <b>Recomendaciones:</b> ${errorDto.recomen} `,
      icon: 'error',
      showClass: {
        popup: 'animate__animated animate__fadeInDown',
      },
      hideClass: {
        popup: 'animate__animated animate__fadeOutUp',
      },
    });
  }

  fireConfirm(
    icon: SweetAlertIcon,
    title: string,
    text: string,
    myCallBack: any
  ) {
    Swal.fire({
      title: title,
      text: text,
      icon: icon,
      reverseButtons: true,
      showCancelButton: true,
      confirmButtonText: 'Si',
      cancelButtonText: 'No',
      confirmButtonColor: '#4CAF50',
      cancelButtonColor: '#f44336',
    }).then((result) => {
      if (result.isConfirmed) {
        myCallBack();
      }
    });
  }
}
