import { Directive, ElementRef, HostListener, Input, Renderer2, OnInit} from '@angular/core';

@Directive({
    selector: '[appHighlights]',
})

export class HighlightsDirective implements OnInit {
    @Input() appHighlights: string = '';
    @Input() defaultColor: string = 'transparent';

    constructor(private element: ElementRef,
        private renderer: Renderer2
    ) { }

    ngOnInit(): void {
        this.setBackgroundColor(this.defaultColor);
    }

    @HostListener('mouseenter')
    onMouseEnter(): void {
        const color = this.appHighlights || '#eef2a1'; // Default highlight color)';

        this.setBackgroundColor(color);
    }

      @HostListener('mouseleave')
    onMouseLeave(): void {
    
        this.setBackgroundColor(this.defaultColor);
    }

    private setBackgroundColor(color: string): void {
        this.renderer.setStyle(
            this.element.nativeElement,
            'background-color',
            color
        )

        this.renderer.setStyle(
            this.element.nativeElement, 'transition', 'background-color 0.3s ease'
        );
    }
}



