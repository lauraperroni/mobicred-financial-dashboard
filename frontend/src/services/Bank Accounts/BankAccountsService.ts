import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

export const getBankAccounts = async () => {
    const token = await getToken();
    if (token) {
        return Api.get('/bankaccounts/user/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const deleteBankAccounts = async (id: number) => {
    const token = await getToken();
    if (token) {
        return Api.delete(`/bankaccounts/delete/${id}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const postBankAccounts = async (formData: any) => {
    const token = await getToken();
    if (token) {
        return Api.post(`/bankaccounts/new`, formData, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const putBankAccounts = async (id: number, formData: any) => {
    const token = await getToken();
    if (token) {
        try {
            const response = await Api.put(`/bankaccounts/update/${id}`, formData, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            return response.data;
        } catch (error) {
            throw new Error(`Erro ao atualizar conta bancária.`);
        }
    }
}

export const BankAccountsService = {
    deleteBankAccounts,
    putBankAccounts,
    getBankAccounts,
    postBankAccounts
}
