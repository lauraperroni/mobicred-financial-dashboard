import React, { useState, useEffect, ChangeEvent } from 'react';
import { format } from 'date-fns';
import { UserService } from '../../services/User/UserService';
import EditPasswordModal from '../cards/EditPasswordModal';

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

const ProfileDetails = () => {
    const [user, setUser] = useState<User | null>(null);
    const [editedUser, setEditedUser] = useState<User | null>(null);
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await UserService.getUser();

                if (response && response.data) {
                    const formattedRegisterDate = format(new Date(response.data.registerDate), 'dd/MM/yyyy');
                    response.data.registerDate = formattedRegisterDate;
                    setUser(response.data);
                    setEditedUser(response.data); // Inicializa o estado de edição com os dados atuais do usuário
                }
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        fetchUser();
    }, []);

    const handleEditClick = () => {
        setIsEditing(true);
    };

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        if (editedUser) {
            const { name, value } = event.target;
            setEditedUser({ ...editedUser, [name]: value });
        }
    };

    const handleSaveClick = async () => {
        try {
            if (editedUser) {
                const response = await UserService.putUser(editedUser);
                if (response && response.status === 200) {
                    setIsEditing(false);
                    setUser(editedUser); // Atualiza o estado do usuário exibido com os dados editados
                }
            }
        } catch (error) {
            console.error('Error updating user data:', error);
        }
    };

    const handleCancelClick = () => {
        setIsEditing(false);
        // Reinicializa o estado de edição com os dados atuais do usuário se o usuário cancelar a edição
        setEditedUser(user);
    };

    return (
        <div className="flex justify-center items-center h-full p-16 bg-gray-50">
            {user && (
                <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                    <h3 className="text-lg font-bold"> User Information</h3>
                    <p className="text-sm font-semibold text-gray-600 mt-4 mb-4">User since {user.registerDate}</p>
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
                    </div>
                    <div className="flex justify-center items-center mt-4">
                        <div className="flex justify-center">
                            <button onClick={handleEditClick} className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-8 rounded">Edit</button>
                        </div>
                        <div className="flex justify-center">
                            <EditPasswordModal />
                        </div>
                    </div>
                </div>
            )}

            {isEditing && editedUser && (
                <div className="fixed z-10 inset-0 overflow-y-auto">
                    <div className="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                        <div className="fixed inset-0 transition-opacity">
                            <div className="absolute inset-0 bg-gray-500 opacity-75"></div>
                        </div>
                        <span className="hidden sm:inline-block sm:align-middle sm:h-screen"></span>&#8203;
                        <div className="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                            <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                                <h3 className="text-lg font-bold mb-4">Edit User Information</h3>
                                <div className="grid grid-cols-2 gap-4">
                                    <input
                                        type="text"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.name}
                                        onChange={handleInputChange}
                                        name="name"
                                        placeholder="Full name"
                                        title="Full name"
                                    />
                                    <input
                                        type="email"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.email}
                                        onChange={handleInputChange}
                                        name="email"
                                        placeholder="Email address"
                                        title="Email address"
                                    />
                                    <input
                                        type="text"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.cpf}
                                        onChange={handleInputChange}
                                        name="cpf"
                                        placeholder="CPF"
                                        title="CPF"
                                    />
                                    <input
                                        type="text"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.street}
                                        onChange={handleInputChange}
                                        name="street"
                                        placeholder="Street"
                                        title="Street"
                                    />
                                    <input
                                        type="number"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.number}
                                        onChange={handleInputChange}
                                        name="number"
                                        placeholder="Number"
                                        title="Number"
                                    />
                                    <input
                                        type="text"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.district}
                                        onChange={handleInputChange}
                                        name="district"
                                        placeholder="District"
                                        title="District"
                                    />
                                    <input
                                        type="text"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.city}
                                        onChange={handleInputChange}
                                        name="city"
                                        placeholder="City"
                                        title="City"
                                    />
                                    <input
                                        type="text"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.state}
                                        onChange={handleInputChange}
                                        name="state"
                                        placeholder="State"
                                        title="State"
                                    />
                                    <input
                                        type="text"
                                        className="mb-4 block w-full border-gray-300 rounded-md shadow-sm focus:ring-green-500 focus:border-green-500 sm:text-sm"
                                        value={editedUser.zipCode}
                                        onChange={handleInputChange}
                                        name="zipCode"
                                        placeholder="Zip code"
                                        title="Zip code"
                                    />
                                </div>
                            </div>
                            <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
                                <button onClick={handleSaveClick} className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-green-500 text-base font-medium text-white hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 sm:ml-3 sm:w-auto sm:text-sm">
                                    Save
                                </button>
                                <button onClick={handleCancelClick} className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus                                    :ring-2 focus:ring-offset-2 focus:ring-gray-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ProfileDetails;

