import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

export const getUser = async () => {
    const token = await getToken();
    if (token) {
        return Api.get('/users/user/{id}', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const putUser = async (formData: any) => {
    const token = await getToken();
    if (token) {
        return Api.put('/users/update', formData, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const UserService = {
    getUser,
    putUser
}