# ProjectPilot 🚀

**ProjectPilot** is a project management website that helps users organize their work, work together with team members, and keep track of tasks. It makes managing projects easier by allowing users to add team members, assign tasks, send email invitations, and chat in real-time. There’s also a subscription feature where users can pay to unlock extra tools using Stripe.

## Features ✨

- **Manage Projects**: Create and organize projects with tasks.
- **Work Together**: Add team members and assign specific tasks to each person.
- **Invite via Email**: Easily invite people to join your project by sending them an email invitation.
- **Assign Tasks**: Divide tasks between team members and set priorities.
- **Real-Time Chat**: Communicate with your team using a chat feature that updates in real-time.
- **Subscription Plans**: Users can pay for premium features through Stripe’s payment system.

## Tech Stack 🛠️

- **Frontend**: `React`, `Tailwind CSS`, `ShadCN UI`
- **Backend**: `Java`, `Spring Boot`
- **Security**: `Spring Security`
- **Database**: `PostgresQl`
- **Payments**: `Stripe API` for handling subscriptions and payments

## Environment Variables

To run this project, you will need to configure the following environment variables:

```bash
stripe.api.key=<Your Stripe API Key>
spring.datasource.url=<Your Database URL>
spring.datasource.username=<Your Database Username>
spring.datasource.password=<Your Database Password>
```

## Screenshots
![PP1](https://github.com/user-attachments/assets/b25ea658-7e6a-4aba-aabc-6893ef98f938)
![PP2](https://github.com/user-attachments/assets/f9ff4a7c-9f2b-48f6-81f5-069c367a856e)
![PP4](https://github.com/user-attachments/assets/8d2df702-306e-4e91-a6e1-56b9ff7fe5ba)
![PP5](https://github.com/user-attachments/assets/2f9e561b-ffb0-4d26-97d8-0fc392db7be8)
![PP3](https://github.com/user-attachments/assets/c0a9d622-a6c0-4f3e-aaec-d1d4f5f64ec4)

## Lessons Learned

- **Task Management**: Built user-friendly tools for managing and tracking tasks.
- **Payment Integration**: Integrated Stripe to handle paid subscription services.
- **Email Service**: Implemented email invitation functionality using `Spring Mail Starter` to invite users to projects.
