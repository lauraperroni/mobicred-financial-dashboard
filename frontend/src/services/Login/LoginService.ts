import { LoginForm } from "../../pages/Login"
import { Api } from "../../providers"


const login = (data: LoginForm) => Api.post('/auth/login', data)

export const LoginService = {
    login
}