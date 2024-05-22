import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Luv2ShopFormService {

  constructor() { }

  getCreditCardMonths(startMonth: number): Observable<number[]> {
    let data: number [] = [];
    // Build an array for month dropdown list. Start at current month and loop untill 12
    for (let theMonth = startMonth; theMonth <= 12; theMonth ++){
      data.push(theMonth);
    }

    return of(data);
  }

  getCreditCardYears(): Observable<number[]> {
    let data: number [] = [];
    // Build array for year dropdown list. start at current year and loop for next 10 years
    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;

    for (let theYear = startYear; theYear <= endYear; theYear ++){
      data.push(theYear);
    }
    return of (data);
  }
}
