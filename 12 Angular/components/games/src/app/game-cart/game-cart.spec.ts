import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameCart } from './game-cart';

describe('GameCart', () => {
  let component: GameCart;
  let fixture: ComponentFixture<GameCart>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameCart],
    }).compileComponents();

    fixture = TestBed.createComponent(GameCart);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
