@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page
@template.layout.page(content = @`
    @if(page.getName() != null)
        <div class="mb-3">
            ${page.getName().toString()}
        </div>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="submit" class="btn btn-primary" value="Выйти"/>
        </form>
    @else
        <div class="mb-3">
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        </div>
    @endif
`)
