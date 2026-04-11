import { Directive, ElementRef, HostBinding, inject, OnInit } from '@angular/core';
import { NgControl } from '@angular/forms';


@Directive({
  selector: '[appInputError]',
})
export class InputErrorDirective implements OnInit {
 private elementRef = inject(ElementRef);

 private control = inject(NgControl, {optional:true});

 @HostBinding('class-input-error') get hasError(): boolean {
  return (this.control?.invalid && this.control?.touched) || false;
 }

 ngOnInit(): void { // because i want to show error only when there is one
   this.elementRef.nativeElement.classList.remove('input-error');
 }
 }