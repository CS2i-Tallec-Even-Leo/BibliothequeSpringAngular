import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { TimeoutError, throwError } from 'rxjs';
import { catchError, timeout } from 'rxjs/operators';

const REQUEST_TIMEOUT_MS = 10000;

export const apiTimeoutInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    timeout(REQUEST_TIMEOUT_MS),
    catchError((error) => {
      if (error instanceof TimeoutError) {
        return throwError(
          () =>
            new HttpErrorResponse({
              status: 408,
              statusText: 'Request Timeout',
              url: req.url,
              error: 'La requete a expire. Verifiez le backend et la configuration CORS.',
            }),
        );
      }

      return throwError(() => error);
    }),
  );
};
