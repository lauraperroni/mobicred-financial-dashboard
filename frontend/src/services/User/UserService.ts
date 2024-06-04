import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

export const getUser = async (id: number) => {
    const token = await getToken();
    if (token) {
        return Api.get(`=/users/${id}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const UserService = {
    getUser
}