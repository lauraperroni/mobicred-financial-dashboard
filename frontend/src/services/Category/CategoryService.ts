import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

const getCategory = async () => {
    const token = await getToken();
    if (token) {
        return Api.get('/categories/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const CategoryService = {
    getCategory
}
