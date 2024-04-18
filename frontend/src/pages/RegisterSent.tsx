import mobicredLogo from '../assets/mobicred.png';

const ResisterSent = () => {
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
                            Register
                        </h2>
                        <div>
                            <p className="mt-10 text-center text-sm text-gray-500">
                                We sent a verification code to the email informed to validate your new account.
                            </p>
                            <p className="mt-5 text-center text-sm text-gray-500">
                                Please check your inbox.
                            </p>
                        </div>
                    </div>
                    
                    {/* "RETURN" LINK*/}
                    <a href="/" className="mt-10 text-center font-semibold leading-6 text-green-500 hover:text-green-600">
                        Return
                    </a>
                </div>
            </div >
        </>
    )
};

export default ResisterSent