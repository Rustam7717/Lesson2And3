package com.example.lesson2and3.utils;

import com.example.lesson2and3.data.models.Post;

public interface OnItemClickListener {
    void onClick(int position);
    void onLongClickListener(Post post);
}
