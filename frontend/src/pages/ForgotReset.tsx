import { useState, FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import mobicredLogo from '../assets/mobicred.png';
import { putPassword } from '../services/User/UserService'; // Importe a função putPassword do seu serviço de usuário
import backgroundImage from '../assets/4.png';

const ForgotReset = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        if (password !== confirmPassword) {
            alert("Passwords don't match");
            return;
        }

        try {
            console.log('Email:', email);
            console.log('New Password:', password);

            const formData = {
                email: email,
                newPassword: password
            };

            const response = await putPassword(formData);
            console.log('PUT Password Response:', response); // Verifique a resposta do servidor, se necessário

            navigate('/'); // Redirecione após o sucesso
        } catch (error) {
            console.error('Error changing password:', error);
            // Tratar erros apropriadamente, como exibir uma mensagem para o usuário
        }
    };

    return (
        <div className='flex w-screen h-screen'>
            {/* Esquerda */}
            <div className='w-4/6 bg-cover bg-center flex justify-center items-center text-white' style={{ backgroundImage: `url(${backgroundImage})` }}>
                <div className='px-4 py-10 text-white'>
                    <h1 className='text-5xl font-extrabold leading-tight mb-4'>Financial Dashboard</h1>
                    <p className='text-xl leading-relaxed'>Your personal financial dashboard</p>
                </div>
            </div>
            {/* import backgroundImage from '../assets/4.png'; */}

            {/* Direita */}
            <div className="flex items-center flex-1 flex-col justify-center px-6 py-12 lg:px-8">
                <div className="flex flex-col sm:mx-auto sm:w-full sm:max-w-sm justify-center ">
                    {/* Logo */}
                    <div className="flex justify-center">
                        <img src={mobicredLogo} alt="Logo" style={{ width: '50%' }} />
                    </div>
                    <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                        Reset your password
                    </h2>
                </div>

                {/* Formulário de email */}
                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form onSubmit={handleSubmit} className="space-y-2">
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
                        <button type="submit" className="w-full text-white bg-green-600 hover:bg-green-700 focus:ring-4 focus:outline-none focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">Reset password</button>
                        {/* Link para fazer login */}
                        <p className="mt-10 text-center text-sm text-gray-500">
                            Already a member?{' '}
                            <a href="/" className="font-semibold leading-6 text-green-500 hover:text-green-600">
                                Login
                            </a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default ForgotReset;
