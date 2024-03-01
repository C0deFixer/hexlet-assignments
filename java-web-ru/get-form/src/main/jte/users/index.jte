@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page
@template.layout.pageUsers(
    content = @`
    <form action="/users" method="get">
        <input type="search" required name="term" value="${page.getTerm()}">
        <input type="submit" value="Искать">
    </form>

    @for(User user : page.getUsersList())
        <div>
            <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
        </div>
        @endfor
    `
)