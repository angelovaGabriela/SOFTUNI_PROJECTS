import { Validator, ValidatorFn, AbstractControl, ValidationErrors } from "@angular/forms";
import { required } from "@angular/forms/signals";


export function emailValidator(): ValidatorFn {

    return (control: AbstractControl): ValidationErrors | null => {
        const value = control.value;

        if (!value) {
            return { required: true }; // No value, so it's valid 
        }

        const emailRegex = /^[a-zA-Z0-9._%+-]{6,}@gmail\.(bg|com)$/;

        if (!emailRegex.test(value)) {
            return { invalidEmail: true };
        }
        return null;

    }

}