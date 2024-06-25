import mobicredLogo from '../assets/mobicred.png';
import backgroundImage from '../assets/4.png';

const ResisterSent = () => {
    return (
        <>
            {/*LEFT SIDE OF THE SCREEN*/}
            <div className='flex w-screen h-screen'>
                <div className='w-4/6 bg-cover bg-center flex justify-center items-center text-white' style={{ backgroundImage: `url(${backgroundImage})` }}>
                    <div className='px-4 py-10 text-white'>
                        <h1 className='text-5xl font-extrabold leading-tight mb-4'>Financial Dashboard</h1>
                        <p className='text-xl leading-relaxed'>Your personal financial dashboard</p>
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