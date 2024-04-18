import mobicredLogo from '../assets/mobicred.png';
import { useNavigate } from 'react-router-dom';

const Forgot = () => {
    const navigate = useNavigate();

    const handleFormSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        // Aqui você pode adicionar a lógica para enviar o formulário, se necessário
        // Após o envio bem-sucedido, redirecione o usuário para a página "/forgot-sent"
        navigate('/forgot-sent');
    };

    return (
        <>
            {/*LEFT SIDE OF THE SCREEN*/}
            <div className='flex w-screen h-screen'>
                <div className='w-4/6 bg-gradient-to-t from-black to-[#00ff26] flex justify-center items-center text-white'> {/* A div da esquerda */}
                    <div className='px-4 py-16'>
                        <h1 className='text-left text-4xl'>Financial Dashboard</h1>
                        <p className='text-left'>Your personal financial dashboard</p>
                    </div>
                </div>

                {/*RIGHT SIDE OF THE SCREEN*/}
                <div className="flex items-center flex-1 flex-col justify-center px-6 py-12 lg:px-8">
                    <div className="flex flex-col sm:mx-auto sm:w-full sm:max-w-sm justify-center ">
                        {/*LOGO*/}
                        <div className="flex justify-center">
                            <img src={mobicredLogo} alt="Logo" style={{ width: '50%' }} />
                        </div>
                        <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                            Reset your password
                        </h2>
                        <div>
                            <p className="mt-10 text-center text-sm text-gray-500">
                                We will send a verification code to the email below with instructions to reset your password.
                            </p>
                        </div>
                    </div>

                    {/*EMAIL INPUT*/}
                    <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                        <form onSubmit={handleFormSubmit} className="space-y-2">
                            <div>
                                <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">
                                    Email address
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="email"
                                        name="email"
                                        type="email"
                                        autoComplete="email"
                                        required
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

                            {/*SUBMIT BUTTON*/}
                            <div>
                                <button
                                    type="submit"
                                    className="flex w-full justify-center rounded-md bg-green-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
                                >
                                    Send
                                </button>
                            </div>
                        </form>

                        {/* "ALREADY A MEMBER? LOG IN" LINK*/}
                        <p className="mt-10 text-center text-sm text-gray-500">
                            Already a member?{' '}
                            <a href="/" className="font-semibold leading-6 text-green-500 hover:text-green-600">
                                Login
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Forgot;
