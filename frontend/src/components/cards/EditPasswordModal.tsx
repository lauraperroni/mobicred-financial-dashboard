import React, { useState } from 'react';
import { UserService } from '../../services/User/UserService';

const EditPasswordModal: React.FC = () => {
    const [showModal, setShowModal] = useState(false);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [termsAccepted, setTermsAccepted] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (password === confirmPassword) {
            try {
                const response = await UserService.putPassword({ email, newPassword: password });
                console.log(email, password);
                console.log('Password change response:', response);
                setShowModal(false); // Fechar o modal após a atualização
            } catch (error) {
                console.error('Error updating password:', error);
                alert('Error updating password!');
            }
        } else {
            alert('Passwords do not match!');
        }
    };

    return (
        <div className="container mx-auto p-8">
            <button onClick={() => setShowModal(true)} className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-8 rounded">
                Change Password
            </button>
            {showModal && (
                <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
                    <div className="bg-gray-50 dark:bg-gray-900 rounded-lg shadow-lg w-80">
                        <div className="bg-green-500 text-white px-4 py-2 rounded-t-lg">
                            <h2 className="text-xl font-semibold pt pb-2">Change Password</h2>
                        </div>
                        <div className="flex flex-col items-center p-4">
                            <form className="mt-4 space-y-4" onSubmit={handleSubmit}>
                                <div>
                                    <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                                    <input type="email" name="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-green-500 focus:border-green-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-green-500 dark:focus:border-green-500" placeholder="name@company.com" required />
                                </div>
                                <div>
                                    <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">New Password</label>
                                    <input type="password" name="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="••••••••" className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-green-500 focus:border-green-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-green-500 dark:focus:border-green-500" required />
                                </div>
                                <div>
                                    <label htmlFor="confirm-password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Confirm password</label>
                                    <input type="password" name="confirm-password" id="confirm-password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} placeholder="••••••••" className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-green-500 focus:border-green-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-green-500 dark:focus:border-green-500" required />
                                </div>
                                <div className="flex items-start">
                                </div>
                                <button type="submit" className="w-full text-white bg-green-600 hover:bg-green-700 focus:ring-4 focus:outline-none focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">Reset password</button>
                                <button type="button" onClick={() => setShowModal(false)} className="w-full mt-2 text-gray-900 bg-gray-300 hover:bg-gray-400 focus:ring-4 focus:outline-none focus:ring-gray-200 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-gray-700 dark:hover:bg-gray-600 dark:focus:ring-gray-600">Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default EditPasswordModal;