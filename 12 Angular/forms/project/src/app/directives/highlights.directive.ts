import { Directive, ElementRef, HostListener, Input, Renderer2, OnInit} from '@angular/core';

@Directive({
    selector: '[appHighlights]',
})

export class HighlightsDirective implements OnInit {
    @Input() appHighlights: string = '';
    @Input() defaultColor: string = '#1e293b';
    @Input() status: 'planned' | 'active' | 'completed' | '' = '';

    constructor(private element: ElementRef,
        private renderer: Renderer2
    ) { }

    ngOnInit(): void {
        this.setBackgroundColor(this.defaultColor);
    }

    @HostListener('mouseenter')
    onMouseEnter(): void {
        let color = this.appHighlights || '#eef2a1'; // Default highlight color

        if (this.status) {
            switch (this.status) {
                case 'planned':
                    color = '#3b82f6'; // Blue
                    break;
                case 'active':
                    color = '#22c55e'; // Green
                    break;
                case 'completed':
                    color = '#64748b'; // Gray
                    break;
            }
        }

        this.setBackgroundColor(color);
        this.renderer.addClass(this.element.nativeElement, 'hovered');
    }

      @HostListener('mouseleave')
    onMouseLeave(): void {
    
        this.setBackgroundColor(this.defaultColor);
        this.renderer.removeClass(this.element.nativeElement, 'hovered');
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



