@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page
@template.layout.page(content = @`
    <p>
        <a href="${NamedRoutes.postsPath()}">Полный список статей</a>
    </p>
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif
    <form action="${NamedRoutes.postsPath()}" method="post">
        <div>
            <label>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Title</span>
                    <input type="text" name="name" class="form-control" placeholder="Название поста" aria-label="Title" aria-describedby="basic-addon1" value="${page.getName()}" >
                </div>
            </label>
        </div>
        <div>
            <label>
                <div class="input-group">
                    <span class="input-group-text">Текст Поста</span>
                    <textarea name="body" class="form-control" aria-label="Текст статьи" placeholder="Текст Статьи" >${page.getBody()}</textarea>
                </div>
            </label>
        </div>
        <input type="submit" value="Создать Пост" />
    </form>
`)