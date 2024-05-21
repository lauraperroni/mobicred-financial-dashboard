// import { PostBankAccountsForm } from "../../components/lists/Accounts"
import { Api } from "../../providers"

const getToken = () => Promise.resolve(localStorage.getItem('token'));

// // POST nova conta bancária
// const postBankAccount = async  (data: PostBankAccountsForm) =>  {
//     const token = await getToken();
//     if(token){
//         return Api.post('/bankaccounts/new', data, {
//             headers: {
//                 'Authorization': `Bearer ${token}`
//             }
//         });
//     }
// }

// GET todas as contas bancárias
const getBankAccounts = async  () =>  {
    const token = await getToken();
    if(token){
        return Api.get('/bankaccounts/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
}

export const BankAccountsService = {
    // postBankAccount, 
    getBankAccounts
}