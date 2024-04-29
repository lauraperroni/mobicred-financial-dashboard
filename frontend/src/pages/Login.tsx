import mobicredLogo from '../assets/mobicred.png';

const Login = () => {
  return (
    <>
      {/*LEFT SIDE OF SCREEN*/}
      <div className='flex w-screen h-screen'> {/* A div grandona que tem tudo */}
        <div className='w-4/6 bg-gradient-to-t from-black to-[#00ff26] flex justify-center items-center text-white'> {/* A div da esquerda */}
          <div className='px-4 py-16'>
            <h1 className='text-left text-4xl'>Financial Dashboard</h1>
            <p className='text-left'>Your personal financial dashboard</p>
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
              Sign in to your account
            </h2>
          </div>


          <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">

            {/*LOGIN FORM*/}
            <form className="space-y-2" action="#" method="POST">


              {/*EMAIL INPUT*/}
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

              {/*PASSWORD INPUT + FORGOT PASSWORD*/}
              <div>
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
                    id="password"
                    name="password"
                    type="password"
                    autoComplete="current-password"
                    required
                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                  />
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

            {/*"NOT A MEMBER? REGISTER" LINK*/}
            <p className="mt-10 text-center text-sm text-gray-500">
              Not a member?{' '}
              <a href="/register" className="font-semibold leading-6 text-green-500 hover:text-green-600">
                Register
              </a>
            </p>
          </div>
        </div>
      </div>
    </>
  )
};

export default Login