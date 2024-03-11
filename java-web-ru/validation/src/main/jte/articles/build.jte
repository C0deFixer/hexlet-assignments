@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page
@template.layout.page(content = @`
    <p>
        <a href="/articles">Полный список статей</a>
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
    <form action="/articles" method="post">
        <div>
            <label>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Title</span>
                    <input type="text" name="title" class="form-control" placeholder="Название Статьи" aria-label="Title" aria-describedby="basic-addon1" value="${page.getTitle()}" >
                </div>
            </label>
        </div>
        <div>
            <label>
                <div class="input-group">
                    <span class="input-group-text">Текст статьи</span>
                    <textarea name="content" class="form-control" aria-label="Текст статьи" placeholder="Текст Статьи" >${page.getContent()}</textarea>
                </div>
            </label>
        </div>
        <input type="submit" value="Создать Статью" />
    </form>
`)