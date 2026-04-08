import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../../core/services/api.service';
import { Theme } from '../../../shared/interfaces/theme';
import { Post } from '../../../shared/interfaces/post';



@Component({
  selector: 'app-themes-content',
  imports: [],
  templateUrl: './themes-content.component.html',
  styleUrl: './themes-content.component.css',
})
export class ThemesContentComponent implements OnInit {
  private apiService = inject(ApiService);
  private route = inject(ActivatedRoute);

  theme: Theme | null = null;
  posts: Post[] = [];
  commentText = '';
  themeId = '';

  ngOnInit(): void {
    this.themeId = this.route.snapshot.params['themeId'];
    this.loadThemeData();
  }


  loadThemeData (): void {
    this.apiService.getThemes().subscribe((themes) =>{
      this.theme = themes.find((t) => t._id === this.themeId ) || null;
    })

    this.apiService.getLatestPosts().subscribe((posts) =>{
      this.posts = posts.filter((p: any) => p.themeId?._id === this.themeId);
    })
  }
}
