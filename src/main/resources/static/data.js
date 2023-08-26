const url = 'http://localhost:8080/api/admin';
const userUrl = 'http://localhost:8080/api/user';

function getAllUsers() {
    fetch(url)
    .then(response => response.json())
    .then(user =>
        loadTable(user))
}

function getUserPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformationAboutUser(user))
}



function loadTable(listAllUsers) {
    let res = '';
    for (let user of listAllUsers) {
        res +=
            `<tr>
                <td>${user.id}</td>             
                <td>${user.userName}</td>
                <td>${user.age}</td>
                <td id=${'role' + user.id}>${user.role.map(r => r.role.substring(5)).join(', ')}</td>
                <td>
                    <button class="btn btn-info" type="button"
                    data-bs-toggle="modal" data-bs-target="#editModal"
                    onclick="editModal(${user.id})">Edit</button></td>
                <td>
                    <button class="btn btn-danger" type="button"
                    data-bs-toggle="modal" data-bs-target="#deleteModal"
                    onclick="deleteModal(${user.id})">Delete</button></td>
            </tr>`
    }
    document.getElementById('tableBodyAdmin').innerHTML = res;
}


function getInformationAboutUser(user) {

    let result = '';
    result =

        `<tr>
    <td>${user.id}</td>
    <td>${user.userName}</td>
    <td>${user.age}</td>
    <td id=${'role' + user.id}>${user.role.map(r => r.role.substring(5)).join(', ')}</td>
</tr>`
    document.getElementById('userTableBody').innerHTML = result;
}

getUserPage();
getAllUsers();

// Новый юзер
document.getElementById('newUserForm').addEventListener('submit', (e) => {
    e.preventDefault()
    let role = document.getElementById('role_select')
    let rolesAddUser = []
    let rolesAddUserValue = ''
    for (let i = 0; i < role.options.length; i++) {
        if (role.options[i].selected) {
            rolesAddUser.push({id: role.options[i].value, name: 'ROLE_' + role.options[i].innerHTML})
            rolesAddUserValue += role.options[i].innerHTML
        }
    }
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            userName: document.getElementById('newUserName').value,
            age: document.getElementById('newAge').value,
            password: document.getElementById('newPassword').value,
            role: rolesAddUser
        })
    })
        .then((response) => {
            if (response.ok) {
                getAllUsers()
                document.getElementById("all-users-tab").click()
            }
        })
})

//Изменение юзера
function editModal(id) {
    fetch(url + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(u => {

            document.getElementById('editId').value = u.id;
            document.getElementById('editUserName').value = u.userName;
            document.getElementById('editAge').value = u.age;
            document.getElementById('editPassword').value = u.password;

        })
    });
}


async function editUser() {
    const form_ed = document.getElementById('modalEdit');

    let idValue = document.getElementById("editId").value;
    let userNameValue = document.getElementById("editUserName").value;
    let ageValue = document.getElementById('editAge').value;
    let passwordValue = document.getElementById("editPassword").value;
    let listOfRole = [];
    for (let i = 0; i < form_ed.role.options.length; i++) {
        if (form_ed.role.options[i].selected) {
            let tmp = {};
            tmp["id"] = form_ed.role.options[i].value
            listOfRole.push(tmp);
        }
    }
    let user = {
        id: idValue,
        userName: userNameValue,
        age: ageValue,
        password: passwordValue,
        role: listOfRole
    }
    await fetch(url + '/' + user.id, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(user)
    });
    closeModal()
    getAllUsers()
}

// Удаление юзера
function deleteModal(id) {
    fetch(url + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(u => {
            document.getElementById('deleteId').value = u.id;
            document.getElementById('deleteUserName').value = u.userName;
            document.getElementById('deleteAge').value = u.age;
            document.getElementById("deleteRole").value = u.role.map(r => r.role.substring(5)).join(", ");
        })
    });
}

async function deleteUser() {
    const id = document.getElementById("deleteId").value
    console.log(id)
    let urlDel = url + "/" + id;
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(urlDel, method).then(() => {
        closeModal()
        getAllUsers()
    })
}

function closeModal() {
    // document.getElementById("editClose").click()
    document.querySelectorAll(".btn-close").forEach((btn) => btn.click())
}

