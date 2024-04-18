
import { BrowserRouter, Route, Routes } from 'react-router-dom'

import Login from './pages/Login'
import Register from './pages/Register'
import Forgot from './pages/Forgot'
import ForgotSent from './pages/ForgotSent'
import RegisterSent from './pages/RegisterSent'
import Home from './pages/Home'
import Transactions from './pages/Transactions'
import Goals from './pages/Goals'
import BankAccounts from './pages/BankAccounts'
import Calculators from './pages/Calculators'
import Profile from './pages/Profile'



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
          <Route path='/home' element={<Home/>} />
          <Route path='/transactions' element={<Transactions/>} />
          <Route path='/goals' element={<Goals/>} />
          <Route path='/profile' element={<Profile/>} />
          <Route path='/bank-accounts' element={<BankAccounts/>} />
          <Route path='/calculators' element={<Calculators/>} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
