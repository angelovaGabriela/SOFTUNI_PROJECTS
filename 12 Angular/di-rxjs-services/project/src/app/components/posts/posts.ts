import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/post.interface';
import { PostsService } from '../../services/posts.service';

@Component({
  selector: 'app-posts',
  imports: [],
  templateUrl: './posts.html',
  styleUrl: './posts.css',
})
export class Posts {
  posts: Post[] = [];

  constructor(private postService: PostsService) {}

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(): void {
    this.postService.getAllPosts().subscribe({
      next: (data) => {
        this.posts = data.slice(0, 6);
      },

      error: (err) => {
        console.error(err);
      }
    })
  }

  deletePost(id: number): void {
    this.postService.deletePost(id).subscribe({
      next: () => {
        this.posts = this.posts.filter(p => p.id !== id);
      },
        error: (err) => {
        console.error(err);
      }
    })
  }
}
