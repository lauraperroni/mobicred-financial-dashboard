// services/FinancialGoalsService.ts

import { Api } from "../../providers";

const getToken = () => Promise.resolve(localStorage.getItem('token'));

export const getFinancialGoals = async () => {
    const token = await getToken();
    if (token) {
        return Api.get('/financialgoals/user/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
};

export const deleteFinancialGoal = async (id: number) => {
    const token = await getToken();
    if (token) {
        return Api.delete(`/financialgoals/delete/${id}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
};

export const createFinancialGoal = async (formData: any) => {
    const token = await getToken();
    if (token) {
        return Api.post(`/financialgoals/new`, formData, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
};

export const updateFinancialGoal = async (id: number, formData: any) => {
    const token = await getToken();
    if (token) {
        return Api.put(`/financialgoals/update/${id}`, formData, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }
};

export const FinancialGoalsService = {
    getFinancialGoals,
    deleteFinancialGoal,
    createFinancialGoal,
    updateFinancialGoal
};
