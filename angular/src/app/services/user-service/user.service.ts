import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { User } from '../../model/entity/user';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  usersUrl = 'http://localhost:8083/users';

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl + '/all')
      .pipe(
        catchError(this.handleError<User[]>('getUseres', []))
      );
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(this.usersUrl + '/byId/' + id)
      .pipe(
        catchError(this.handleError<User>(`getUserById id=${id}`))
      );
  }

  private handleError<T>(operation = 'operation', result?: T): (error: any) => Observable<T> {
    return (error: any): Observable<T> => {

      // better job of transforming error for user consumption
      console.error(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
