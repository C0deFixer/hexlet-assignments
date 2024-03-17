@import exercise.util.NamedRoutes
@import exercise.repository.UserRepository
@template.layout.page(content = @`
    <a href="${NamedRoutes.buildUserPath()}">Создать user</a>
    <table class="table table-striped">
        @for(var user : UserRepository.getEntities())
            <tr>
                <td>
                    ${user.getId()}
                </td>
                <td>
                    <a href="${NamedRoutes.userPath(user.getId())}">${user.getFirstName()}</a>
                </td>
            </tr>
        @endfor
    </table>
`)