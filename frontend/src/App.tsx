
import { BrowserRouter, Route, Routes } from 'react-router-dom'

import Login from './pages/Login'
import Register from './pages/Register'
import Forgot from './pages/Forgot'
import ForgotSent from './pages/ForgotSent'
import RegisterSent from './pages/RegisterSent'


function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Login/>} />
          <Route path='/register' element={<Register/>} />
          <Route path='/register-sent' element={<RegisterSent/>} />
          <Route path='/forgot-password' element={<Forgot/>} />
          <Route path='/forgot-sent' element={<ForgotSent/>} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
