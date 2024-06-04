import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

const getBankAccounts = async () => {
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

export const postBankAccounts = async (formData: any) => { // Recebe formData para enviar os dados da nova conta bancária
    const token = await getToken();
    if (token) {
        return Api.post(`/bankaccounts/new`, formData, { // Envia formData na requisição POST
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const BankAccountsService = {
    deleteBankAccounts,
    getBankAccounts,
    postBankAccounts // Adiciona a função postBankAccounts ao serviço
}
