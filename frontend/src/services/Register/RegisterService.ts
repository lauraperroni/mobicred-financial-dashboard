import { RegisterForm } from "../../pages/Register"
import { Api } from "../../providers"


const register = (data: RegisterForm) => Api.post('/auth/register', data)

export const RegisterService = {
    register
}