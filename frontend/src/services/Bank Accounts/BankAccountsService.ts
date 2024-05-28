import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

const getBankAccounts = async () => {
    const token = await getToken();
    if (token) {
        return Api.get('/bankaccounts/all', {
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

export const BankAccountsService = {
    deleteBankAccounts,
    getBankAccounts
}