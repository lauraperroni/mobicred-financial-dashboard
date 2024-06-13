import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

const getTransactions = async () => {
    const token = await getToken();
    if (token) {
        return Api.get('/transactions/user/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const deleteTransactions = async (id: number) => {
    const token = await getToken();
    if (token) {
        return Api.delete(`/transactions/delete/${id}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const postTransactions = async (formData: any) => { 
    const token = await getToken();
    if (token) {
        return Api.post(`/transactions/new`, formData, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const putTransactions = async (id: number, formData: any) => { 
    const token = await getToken();
    if (token) {
        return Api.put(`/transactions/update/${id}`, formData, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}


export const TransactionsService = {
    getTransactions,
    deleteTransactions,
    putTransactions,
    postTransactions // Certifique-se de exportar a função de deletar transações
}
