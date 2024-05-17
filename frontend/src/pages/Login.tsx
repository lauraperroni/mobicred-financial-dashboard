import { useForm } from 'react-hook-form';
import mobicredLogo from '../assets/mobicred.png';
import { useNavigate } from 'react-router-dom';
import { LoginService } from '../services';

export interface LoginForm {
  email: string;
  password: string;
}

const Login = () => {
  const navigate = useNavigate();

  const { formState: { errors }, register, handleSubmit } = useForm<LoginForm>()

  async function submit(value: LoginForm) {

    const { status, data } = await LoginService.login(value)
    if (status === 200) {
      localStorage.setItem('token', data.token)
      navigate('/home');
    }
  };

  return (
    <>
      {/*LEFT SIDE OF SCREEN*/}
      <div className='flex flex-col md:flex-row w-full h-screen'>
        <div className='md:w-4/6 bg-gradient-to-t from-black to-[#00ff26] flex flex-col justify-center items-center text-white'>
          <div className='px-4 py-8 md:py-16'>
            <h1 className='text-center md:text-left text-3xl md:text-4xl mb-4'>Financial Dashboard</h1>
            <p className='text-center md:text-left'>Your personal financial dashboard</p>
          </div>
        </div>

        {/*RIGHT SIDE OF SCREEN*/}
        <div className="flex items-center flex-1 flex-col justify-center px-6 py-12 lg:px-8">
          <div className="flex flex-col sm:mx-auto sm:w-full sm:max-w-sm justify-center ">
            {/*LOGO*/}
            <div className="flex justify-center">
              <img src={mobicredLogo} alt="Logo" style={{ width: '50%' }} />
            </div>
            <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
              Login
            </h2>
          </div>

          <div className="mt-2 w-full max-w-md mx-auto">

            {/*LOGIN FORM*/}
            <form onSubmit={handleSubmit(submit)} className="space-y-4">

              {/*EMAIL INPUT*/}
              <div className="mt-6 grid grid-cols-1 gap-4">
                <div className="sm:col-span-4">
                  <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">
                    Email address
                  </label>
                  <div className="mt-2">
                    <input
                      {...register("email")}
                      id="email"
                      name="email"
                      type="email"
                      autoComplete="email"
                      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                    />
                  </div>

                  {/*PASSWORD INPUT + FORGOT PASSWORD*/}
                  <div className="flex items-center justify-between">
                    <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                      Password
                    </label>
                    <div className="text-sm">
                      <a href="/forgot-password" className="font-semibold text-green-500 hover:text-green-600">
                        Forgot password?
                      </a>
                    </div>
                  </div>
                  <div className="mt-2">
                    <input
                      {...register("password")}
                      id="password"
                      name="password"
                      type="password"
                      autoComplete="current-password"
                      required
                      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                    />
                  </div>
                </div>
              </div>

              {/*LOGIN BUTTON*/}
              <div>
                <button
                  type="submit"
                  className="flex w-full justify-center rounded-md bg-green-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
                >
                  Sign in
                </button>
              </div>
            </form>

            {/* "NOT A MEMBER? REGISTER" LINK*/}
            <p className="mt-8 text-center text-sm text-gray-500">
              Not a member?{' '}
              <a href="/register" className="font-semibold text-green-500 hover:text-green-600">
                Register here
              </a>
            </p>
          </div>
        </div>
      </div>
    </>
  )
};

export default Login;
