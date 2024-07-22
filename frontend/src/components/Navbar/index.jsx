import { CompanyName, UserOptions, NavbarContainer, CompanyBranding, Icon } from "./styles"
import logo from "/logo.svg";
import user from "/user.svg";
import logout from "/logout.svg";

const Navbar = () => {
    return (
        <>
            <NavbarContainer>
                <CompanyBranding>
                    <Icon src={logo}></Icon>
                    <CompanyName>CompanyName</CompanyName>
                </CompanyBranding>
                <UserOptions>
                    <Icon src={user}></Icon>
                    <Icon src={logout}></Icon>
                </UserOptions>
            </NavbarContainer>
        </>
    )
}

export default Navbar