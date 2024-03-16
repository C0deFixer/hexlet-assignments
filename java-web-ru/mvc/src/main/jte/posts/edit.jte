@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

@template.layout.page(
content = @`

        @if(page.getErrors() != null)
            <div class="mb-3">
                <ul>
                    @for(var validator : page.getErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            </div>
        @endif

    <div class="mx-auto p-4 py-md-5">
        <form action="${NamedRoutes.postPath(page.getId())}" method="post">
            <div>
                <label>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Title</span>
                        <input type="text" name="name" class="form-control" placeholder="Название Статьи" aria-label="Title" aria-describedby="basic-addon1" value="${page.getName()}" >
                    </div>
                </label>
            </div>
            <div>
                <label>
                    <div class="input-group">
                        <span class="input-group-text">Текст поста</span>
                        <textarea name="body" class="form-control" aria-label="Текст статьи" placeholder="Текст Статьи" >${page.getBody()}</textarea>
                    </div>
                </label>
            </div>
            <input type="submit" value="Сохранить изменения" />
        </form>

    </div>
`
)