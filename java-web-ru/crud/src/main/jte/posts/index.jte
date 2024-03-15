@import exercise.dto.posts.PostsPage
@import exercise.repository.PostRepository
@import exercise.util.NamedRoutes
@param PostsPage page
@template.layout.page(content = @`
    <a href="${NamedRoutes.postPath()}">Создать пост</a>
    <table class="table table-striped">
        @for(var post : page.getEntries())
            <tr>
                <td>
                    ${post.getId()}
                </td>
                <td>
                    <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                </td>
            </tr>
        @endfor
    </table>
    <nav aria-label="...">
        <ul class="pagination">
            @if (page.getCurrentPage() == 1)
                <li class="page-item disabled">
                    <a class="page-link" tabindex="-1" aria-disabled="true" href="#">Previous</a>
                </li>
            @else
                <li class="page-item active">
                    <a class="page-link" tabindex="-1" href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage()-1}">Previous</a>
                </li>
            @endif
            @for(var i = 1; i<=  page.getCountPages(); i++)

                @if (i == page.getCurrentPage())
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="#">${i}</a>
                    </li>
                @else
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${i}">${i}</a></li>
                @endif
            @endfor
            @if (page.getCurrentPage() == page.getCountPages())
                <li class="page-item disabled">
                    <a class="page-link" tabindex="-1" aria-disabled="true" href="#">Next</a>
                </li>
            @else
                <li class="page-item active">
                    <a class="page-link" tabindex="-1" href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage()+1}">Next</a>
                </li>
            @endif

        </ul>
    </nav>
`)