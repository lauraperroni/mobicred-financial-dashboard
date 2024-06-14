import React, { useState, useEffect } from 'react';
import { format } from 'date-fns'; // Importe a função format do date-fns
import { UserService } from '../services/User/UserService';

interface User {
    id: number;
    role: string;
    cpf: string;
    name: string;
    email: string;
    password: string;
    street: string;
    number: number;
    district: string;
    complement: string;
    city: string;
    state: string;
    zipCode: string;
    registerDate: string;
}

const ProfileEdits = () => {
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await UserService.getUser();

                if (response && response.data) {
                    // Formate a data de registro para dd/mm/yyyy
                    const formattedRegisterDate = format(new Date(response.data.registerDate), 'dd/MM/yyyy');
                    // Adicione a data formatada ao objeto do usuário
                    response.data.registerDate = formattedRegisterDate;
                    setUser(response.data);
                    console.log("API results: ", response.data);
                }
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        fetchUser();
    }, []);

    return (
        <div className="flex justify-center items-center h-full p-8 bg-gray-50">
            {user && (
                <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                    <h3 className="text-lg font-bold mb-4">User Information</h3>
                    <div className="grid grid-cols-2 gap-4">
                        <div>
                            <p className="text-sm font-semibold text-gray-600">Full name:</p>
                            <p className="text-sm text-gray-800">{user.name}</p>
                        </div>
                        <div>
                            <p className="text-sm font-semibold text-gray-600">Email address:</p>
                            <p className="text-sm text-gray-800">{user.email}</p>
                        </div>
                        <div>
                            <p className="text-sm font-semibold text-gray-600">CPF:</p>
                            <p className="text-sm text-gray-800">{user.cpf}</p>
                        </div>
                        <div>
                            <p className="text-sm font-semibold text-gray-600">Address:</p>
                            <p className="text-sm text-gray-800">{user.street}, {user.number}</p>
                            <p className="text-sm text-gray-800">{user.district}, {user.city}, {user.state}, {user.zipCode}</p>
                        </div>
                        <div>
                            {/* Exiba a data de registro formatada */}
                            <p className="text-sm font-semibold text-gray-600">User since {user.registerDate}</p>
                        </div>
                    </div>
                    {/* Adicione mais campos conforme necessário */}
                </div>
            )}
        </div>
    );
};

export default ProfileEdits;
