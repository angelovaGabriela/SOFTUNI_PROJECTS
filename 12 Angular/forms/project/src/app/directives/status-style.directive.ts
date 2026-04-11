import { Directive, HostBinding, Input, OnChanges, Renderer2, SimpleChanges} from "@angular/core";

@Directive({
    selector: '[appStatusStyle]'
})

export class StatusStyleDirective implements OnChanges {
 
  
    @Input() appStatusStyle: 'planned' | 'active' | 'completed' | '' = '';

    @HostBinding('style.backgroundColor')
    backgroundColor: string = 'transparent';

    @HostBinding('style.color')
    textColor: string = 'inherit';

    @HostBinding('style.padding')
    padding: string = '4px 12px';

    @HostBinding('style.borderRadius')
    borderRadius: string = '9999px';

    @HostBinding('style.fontWeight')
    fontWeight: string = '500';

    @HostBinding('style.fontSize')
    fontSize: string = '12px';

    @HostBinding('style.textTransform')
    textTransform: string = 'uppercase';


    ngOnChanges(): void {
        this.updateStyles();
    }

    private updateStyles(): void {
        switch (this.appStatusStyle) {
            case 'planned':
                this.backgroundColor = '#3b82f6';
                this.textColor = 'white';
                break;
            case 'active':
                this.backgroundColor = '#22c55e';
                this.textColor = 'white';
                break;
            case 'completed':
                this.backgroundColor = '#64748b';
                this.textColor = 'white';
                break;
            default:
                this.backgroundColor = 'transparent';
                this.textColor = 'inherit';
                break;
        }
    }
}